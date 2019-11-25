package dk.nodes.template.presentation.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    Login_btn.setOnClickListener(this)



    }
}
