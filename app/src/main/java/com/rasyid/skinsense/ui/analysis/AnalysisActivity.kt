package com.rasyid.skinsense.ui.analysis

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rasyid.skinsense.R
import com.rasyid.skinsense.databinding.ActivityAnalysisBinding

class AnalysisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalysisBinding

    private lateinit var questions: Array<String>
    private lateinit var options: Array<Int>
    private val answers = mutableListOf<Int?>()
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

        questions = resources.getStringArray(R.array.questions)
        val typedArray = resources.obtainTypedArray(R.array.options)
        options = Array(typedArray.length()) { typedArray.getResourceId(it, 0) }
        typedArray.recycle()
        answers.addAll(List(questions.size) { null })

        updateQuestionAndAnswer()
        updateButtonState()

        binding.btnStart.setOnClickListener {
            binding.main.transitionToEnd()
        }

        binding.btnNext.setOnClickListener {
            saveAnswer()
            Log.d("AnalysisActivity", "btnNext $answers")
            if (currentIndex < questions.size - 1) {
                currentIndex++
                updateButtonState()
                binding.main.setTransition(R.id.end, R.id.fade)
                binding.main.transitionToStart()
                updateQuestionAndAnswer()
                binding.main.postDelayed({
                    binding.main.setTransition(R.id.fade, R.id.end)
                    binding.main.transitionToStart()
                }, 400)
            }
        }

        binding.btnPrev.setOnClickListener {
            saveAnswer()
            Log.d("AnalysisActivity", "btnPrev $answers")
            if (currentIndex > 0) {
                currentIndex--
                updateButtonState()
                binding.main.setTransition(R.id.end, R.id.fade)
                binding.main.transitionToStart()
                updateQuestionAndAnswer()
                binding.main.postDelayed({
                    binding.main.setTransition(R.id.fade, R.id.end)
                    binding.main.transitionToStart()
                }, 400)
            }
        }

        binding.btnAnalysis.setOnClickListener {
            saveAnswer()
            if (answers.contains(null)) {
                AlertDialog.Builder(this).apply {
                    setTitle("Yuk, Lengkapi Jawaban!")
                    setMessage("Beberapa pertanyaan masih belum terjawab. Lengkapi dulu, ya, agar hasil analisis lebih akurat!")
                    setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    create()
                    show()
                }
            } else {
                Toast.makeText(this, "Berhasil melakukan analisis!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestionAndAnswer() {
        when (answers[currentIndex]) {
            1 -> binding.rb01.isChecked = true
            2 -> binding.rb02.isChecked = true
            3 -> binding.rb03.isChecked = true
            4 -> binding.rb04.isChecked = true
            null -> binding.rgAnswers.clearCheck()
        }
        binding.tvQuestion.text = questions[currentIndex]
        val optionsForCurrent = resources.getStringArray(options[currentIndex])
        for (i in 0 until binding.rgAnswers.childCount) {
            (binding.rgAnswers.getChildAt(i) as RadioButton).text = optionsForCurrent[i]
        }
    }

    private fun saveAnswer() {
        val selectedOption = when (binding.rgAnswers.checkedRadioButtonId) {
            R.id.rb_01 -> 1
            R.id.rb_02 -> 2
            R.id.rb_03 -> 3
            R.id.rb_04 -> 4
            else -> null
        }

        answers[currentIndex] = selectedOption
    }

    private fun updateButtonState() {
        binding.btnNext.isEnabled = currentIndex != questions.size - 1
        binding.btnNext.alpha = if (currentIndex == questions.size - 1) 0.3f else 1.0f
        binding.btnPrev.isEnabled = currentIndex != 0
        binding.btnPrev.alpha = if (currentIndex == 0 ) 0.3f else 1.0f
        binding.btnAnalysis.alpha = if (currentIndex == 13) 1.0f else 0.0f
    }
}