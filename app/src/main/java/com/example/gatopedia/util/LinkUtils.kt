package com.example.gatopedia.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import com.example.gatopedia.R

object LinkUtils {

    private fun createClickableSpan(context: Context, url: String): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun makeLinkClickable(textView: TextView, linkText: String, url: String) {
        val spannableString = SpannableString(linkText)
        val clickableSpan = createClickableSpan(textView.context, url)
        spannableString.setSpan(
            clickableSpan,
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        with(textView){
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            setLinkTextColor(getColor(textView.context, R.color.brown_link))
        }
    }

}