package com.jjw.learnKorean.settings

import android.content.Intent
import android.content.pm.PackageInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_main_settings.view.*



class SettingsFragment: androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val settingsView = inflater.inflate(R.layout.activity_main_settings,container, false)

        settingsView.tv_appVersion.text = getVersionInfo()
        settingsView.layout_sendMail.setOnClickListener(onClickListener)
        settingsView.layout_rateIt.setOnClickListener(onClickListener)
        settingsView.layout_openSource_licence.setOnClickListener(onClickListener)

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
        when (it.id) {

            R.id.layout_rateIt -> {
                val appPackageName = activity!!.packageName
                val playStore = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
                startActivity(playStore)
            }

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

            R.id.layout_openSource_licence -> {
                val filterIntent = Intent(context, OpenSourceActivity::class.java)
                startActivity(filterIntent)
                activity!!.overridePendingTransition(R.anim.slide_in_half, R.anim.fade_out)
            }

        }
    }

}