package com.example.akniyet.finalapp
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_add.*
import java.util.jar.Attributes


class AddActivity : AppCompatActivity {

    private val roomStorage : Dao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        if (intent.getStringExtra("type") == "add") {

            fabBtn.setOnClickListener {

                roomStorage.insert(Contact(0,
                    textID.text.toString(), false, roomStorage.getItems(),
                    textName.text.toString(), false, roomStorage.getItems(),
                    textMobile.text.toString(), false, roomStorage.getItems(),
                    textHome.text.toString(), false, roomStorage.getItems(),
                    textWork.text.toString(), false, roomStorage.getItems()))


                    val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
        else {
            fabBtn.visibility = View.GONE

        }
    }


}

private fun Any.onCreate(savedInstanceState: Bundle?) {

}
