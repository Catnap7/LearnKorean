package com.jjw.learnKorean.setting

import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_main_settings.view.*

class SettingsFragment: androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val settingsView = inflater.inflate(R.layout.activity_main_settings,container, false)

        settingsView.tv_appVersion.text = getVersionInfo()
        settingsView.layout_sendMail.setOnClickListener(onClickListener)

        return settingsView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    fun getVersionInfo() :String {
        val info: PackageInfo = context!!.packageManager.getPackageInfo(context!!.packageName, 0)
        return info.versionName
    }


    private val onClickListener = View.OnClickListener {
        when(it.id){
            R.id.layout_sendMail -> {
                 val email = Intent(Intent.ACTION_SEND)
                 email.type = "plain/text"
                 // email setting 배열로 해놔서 복수 발송 가능
                 val address = arrayOf(context!!.getString(R.string.my_email))
                 email.putExtra(Intent.EXTRA_EMAIL, address)
//                 email.putExtra(Intent.EXTRA_SUBJECT,"보내질 email 제목")
//                 email.putExtra(Intent.EXTRA_TEXT,"보낼 email 내용을 미리 적어 놓을 수 있습니다.\n")
                 startActivity(email)
            }
        }
    }


}