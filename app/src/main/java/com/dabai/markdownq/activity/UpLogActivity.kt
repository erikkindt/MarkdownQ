package com.dabai.markdownq.activity

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager

import com.dabai.markdownq.R

import br.tiagohm.markdownview.MarkdownView
import br.tiagohm.markdownview.css.styles.Github

class UpLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_log)


        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        initTheme()

        val mv = findViewById<MarkdownView>(R.id.uplogmarkdown)
        mv.addStyleSheet(Github())
        mv.loadMarkdownFromAsset("更新日志.md")

    }


    /**
     * 提交与获取
     *
     * @param key
     * @param value
     */
    fun set_sharedString(key: String, value: String) {
        val sp = this.getSharedPreferences("data", 0)
        val editor = sp.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun get_sharedString(key: String, moren: String): String? {
        val sp = this.getSharedPreferences("data", 0)
        return sp.getString(key, moren)
    }


    private fun initTheme() {

        val theme = get_sharedString("theme", "日")
        if (theme == "日") {

        } else {
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#607D8B")))
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val window = this.window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = Color.parseColor("#455A64")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }


    

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.itemId) {
            android.R.id.home -> {
                // 处理返回逻辑
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}