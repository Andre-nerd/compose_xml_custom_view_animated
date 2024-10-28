package ru.zoomparty.xml_compose_custom_view_animation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.zoomparty.xml_compose_custom_view_animation.databinding.FragmentXmlViewBinding

class XmlViewFragment:Fragment() {
    private var _binding:FragmentXmlViewBinding? = null
    private val binding:FragmentXmlViewBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentXmlViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}