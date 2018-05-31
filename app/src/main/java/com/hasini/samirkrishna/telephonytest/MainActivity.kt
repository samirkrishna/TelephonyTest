package com.hasini.samirkrishna.telephonytest

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var et1:EditText?=null
    var et2:EditText?=null
    var et3:EditText?=null
    var et4:EditText?=null
    var et5:EditText?=null
    var uri:Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et1=findViewById(R.id.et1)
        et2=findViewById(R.id.et2)
        et3=findViewById(R.id.et3)
        et4=findViewById(R.id.et4)
        et5=findViewById(R.id.et5)
    }

    fun sendSMS(v:View){
        var list=et1?.text.toString().split(",")
        for(num in list) {

            var send = Intent(this, Send::class.java)
            var delivery = Intent(this, Delivery::class.java)
            var ps = PendingIntent.getActivity(this, 0, send, 0)
            var pd = PendingIntent.getActivity(this, 0, delivery, 0)
            var sManager = SmsManager.getDefault()
            sManager.sendTextMessage(num.toString(), null, et2?.text.toString(), ps, pd)
        }
    }
    fun call(v:View){
        var i=Intent()
        i.setAction(Intent.ACTION_CALL)
        i.setData(Uri.parse("tel:"+et1?.text.toString()))
        startActivity(i)
    }
    fun sendMail(v:View)
    {
        var i=Intent()
        i.setAction(Intent.ACTION_SEND)
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(et3?.text.toString()))//By using arrayOf method multiple mails can be sent to multiple users
        i.putExtra(Intent.EXTRA_SUBJECT,et4?.text.toString())
        i.putExtra(Intent.EXTRA_TEXT,et5?.text.toString())
        i.putExtra(Intent.EXTRA_STREAM,uri)
        i.setType("message/rfc822")
        startActivity(Intent.createChooser(i,"Select Any Email Client...."))
    }
    fun javaMail(v:View){

    }
    fun attach(v:View){

        var i=Intent()
        i.setAction(Intent.ACTION_GET_CONTENT)
        i.setType("*/*")//all types of siles
        startActivityForResult(i,123)//any positive number as request code,we get back the info from another activity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {//after selection is done we can back to previous activity

        uri = data?.data//selected file path is stored in uri object
        super.onActivityResult(requestCode, resultCode, data)


    }
}
