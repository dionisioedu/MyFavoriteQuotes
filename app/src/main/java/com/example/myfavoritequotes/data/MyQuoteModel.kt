package com.example.myfavoritequotes.data

import com.squareup.moshi.Json

//[ {"q":"I learned the value of hard work by working hard. ","a":"Margaret Mead","h":"<blockquote>&ldquo;I learned the value of hard work by working hard. &rdquo; &mdash; <footer>Margaret Mead</footer></blockquote>"} ]

data class MyQuoteModel(
    @Json(name = "q") val text: String,
    @Json(name = "a") val author: String,
    @Json(name = "h") val html: String,
)
