package com.asma_somrani.zpllabelprint

import android.os.AsyncTask.execute
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.somraniasma.zpllabelprint.LabelPrinting


class MainActivity : AppCompatActivity() {
    lateinit var printbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printbtn=findViewById(R.id.btnprint)
        printbtn.setOnClickListener {

            //  val impression: LabelPrinting = LabelPrinting(this@MainActivity)

            var print= LabelPrinting(this@MainActivity,false, "192.168.1.14", 9100, "printing")
            print.execute("") }
    }
}