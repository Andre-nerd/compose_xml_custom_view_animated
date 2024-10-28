package ru.zoomparty.xml_compose_custom_view_animation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.zoomparty.xml_compose_custom_view_animation.R
import ru.zoomparty.xml_compose_custom_view_animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.composeFragment.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ComposeViewFragment>(R.id.fragment_container)
            }
        }
        binding.xmlFragment.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<XmlViewFragment>(R.id.fragment_container)
            }
        }
    }
}



