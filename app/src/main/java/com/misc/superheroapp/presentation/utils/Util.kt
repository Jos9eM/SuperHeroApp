package com.misc.superheroapp.presentation.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.misc.superheroapp.R


fun Context?.isNetworkAvailable(): Boolean {
    if (this == null) return false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    true
                }
                else -> false
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.isLandscape(): Boolean {
    return this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

fun Context.showGlideImg(url: String, imageView: ImageView) {
    Glide.with(this).load(url)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .error(R.drawable.placeholder)
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }).into(imageView)
}

fun RatingBar.showRate(value: String) {
    rating = if (value != "null") ((value.toFloat() * 5) / 100) else 0f
}

fun TextView.textHtml(firstString: String, rest: String) {
    text = HtmlCompat.fromHtml("<b><font color=#FFFFFF> $firstString </></b> $rest", HtmlCompat.FROM_HTML_MODE_COMPACT)
}

fun ImageView.setGender(gender: String){
    when(gender){
        "Male" -> this.setImageResource(R.drawable.ic_male)
        "Female" -> this.setImageResource(R.drawable.ic_fem)
        else -> this.setImageResource(R.drawable.ic_trans)
    }
}


fun ImageView.setAlignment(alignment: String){
    when(alignment){
        "GOOD" -> this.setImageResource(R.drawable.ic_hero)
        "BAD" -> this.setImageResource(R.drawable.ic_villain)
        else -> this.setImageResource(R.drawable.ic_anti_hero)
    }
}
