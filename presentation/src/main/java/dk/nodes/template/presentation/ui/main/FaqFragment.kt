package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dk.nodes.template.presentation.R

class FaqFragment : Fragment(){
    companion object{
        fun newInstance(): FaqFragment{
            return FaqFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_faq_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val faqIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tjenerteamet.dk/faq-app.html"))
        startActivity(faqIntent)


    }
}