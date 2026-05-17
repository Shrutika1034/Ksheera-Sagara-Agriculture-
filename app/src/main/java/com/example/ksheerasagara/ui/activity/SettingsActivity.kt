package com.example.ksheerasagara.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import com.example.ksheerasagara.databinding.ActivitySettingsBinding
import com.example.ksheerasagara.utils.ThemeManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding =
            ActivitySettingsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // LIGHT MODE

        binding.btnLight.setOnClickListener {

            ThemeManager.saveTheme(
                this,
                AppCompatDelegate.MODE_NIGHT_NO
            )

            recreate()
        }

        // DARK MODE

        binding.btnDark.setOnClickListener {

            ThemeManager.saveTheme(
                this,
                AppCompatDelegate.MODE_NIGHT_YES
            )

            recreate()
        }

        // SYSTEM DEFAULT

        binding.btnSystem.setOnClickListener {

            ThemeManager.saveTheme(
                this,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )

            recreate()
        }
    }
}