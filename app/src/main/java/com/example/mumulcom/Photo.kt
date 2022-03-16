//Photo.kt
package com.example.mumulcom // 패키지명

import android.graphics.Bitmap
import java.util.*

class Photo(
    var imageUrl: ByteArray? =null, // 사진이 저장된 경로
    var plus: Int? =null,
    var date: Date =Date()
    )