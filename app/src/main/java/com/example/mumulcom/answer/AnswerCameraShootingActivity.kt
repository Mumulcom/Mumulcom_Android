package com.example.mumulcom.answer

import android.Manifest
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mumulcom.databinding.ActivityAnswercamerashootingBinding
import java.io.File
import java.text.SimpleDateFormat
import androidx.core.content.FileProvider
import java.io.FileOutputStream
import java.io.OutputStream


class AnswerCameraShootingActivity:AppCompatActivity() {
    lateinit var binding: ActivityAnswercamerashootingBinding

    private lateinit var getResultText: ActivityResultLauncher<Intent>

    var imageName // 카메라로 찍은 사진 이름
            : String? = null
    var imageUri // 카메라로 찍은 사진 Uri
            : Uri? = null

    // Permisisons
    val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val PERMISSIONS_REQUEST = 100
    val REQUEST_IMAGE_CAPTURE = 1

    // Request Code
    private val BUTTON1 = 100
    private val BUTTON3 = 300

    // 원본 사진이 저장되는 Uri
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswercamerashootingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkPermissions(PERMISSIONS, PERMISSIONS_REQUEST)

        binding.answercameraCamerashootingCheckIb.setOnClickListener {
            startActivity(Intent(this, AnswerActivity::class.java))
        }

        //카메라버튼
        binding.answercameraCamerashootingCameraIv.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFile = File(
                File("${filesDir}/image").apply{
                    if(!this.exists()){
                        this.mkdir();

                    }
                },
                newJpgFileName()
            )
            photoUri = FileProvider.getUriForFile(
                this,
                "com.example.mumulcom.fileprovider",
                photoFile
            )
            takePictureIntent.resolveActivity(packageManager)?.also{
                takePictureIntent.putExtra("uri", photoUri)
                startActivityForResult(takePictureIntent, BUTTON1)
            }
            Log.d("uri", photoUri.toString())
            //체크버튼
            binding.answercameraCamerashootingCheckIb.setOnClickListener {
                val intent = Intent(this@AnswerCameraShootingActivity, AnswerActivity::class.java)
                takePictureIntent.resolveActivity(packageManager)?.also{
                    takePictureIntent.putExtra("uri", photoUri)
                }
                startActivity(intent)
            }
        }



        //앨범버튼
        //체크버튼
//        binding.camerashootingCheckIb.setOnClickListener {
//            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            val photoFile = File(
//                File("${filesDir}/image").apply{
//                    if(!this.exists()){
//                        this.mkdirs()
//                    }
//                },
//                newJpgFileName()
//            )
//            photoUri = FileProvider.getUriForFile(
//                this,
//                "com.example.mumulcom.fileprovider",
//                photoFile
//            )
//            takePictureIntent.resolveActivity(packageManager)?.also{
//                takePictureIntent.putExtra("uri", photoUri)
//                startActivityForResult(takePictureIntent, BUTTON1)
//            }
//            val intent = Intent(this@CodingCameraShootingActivity, CheckCodingQuestionActivity::class.java)
//            startActivity(intent)
//        }
//        MediaStore.EXTRA_OUTPUT

        binding.answercameraCamerashootingBackIb.setOnClickListener {
            startActivity(Intent(this, AnswerActivity::class.java))
        }

        // 화면이 만들어 지면서 정장소 권한을 체크 합니다.
        // 권한이 승인되어 있으면 카메라를 호출하는 메소드를 실행합니다.
        if (checkPermissions(PERMISSIONS, PERMISSIONS_REQUEST)) {
            //사진 회전시키기
            val imageView=binding.ivPre
            val currentRotation=imageView.rotation
            val currentImageViewHeight = imageView.height
            val displayMetrics = DisplayMetrics()
            val deviceWidth=displayMetrics.widthPixels
            val deviceHeight=displayMetrics.heightPixels


            val  heightGap= if (currentImageViewHeight>deviceWidth){
                deviceWidth-currentImageViewHeight
            }else{
                deviceHeight - currentImageViewHeight
            }

            if (currentRotation%90==0.toFloat()){

                ValueAnimator.ofFloat(0f, 1f).apply {
                    duration=500
                    addUpdateListener {
                        val animatedValue = it.animatedValue as Float
                        imageView.run{
                            layoutParams.height=
                                currentImageViewHeight+(heightGap * animatedValue)
                                    .toInt()
                            rotation=currentRotation+90*animatedValue
                            requestLayout()
                        }
                    }
                }.start()
            }
        }

    }
    //사진 각도 반환 함수
    fun exifOrientationToDegrees(exifOrientation: Int): Int {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270
        }
        return 0
    }

    // 이미지 회전 함수
    private fun rotate(bitmap: Bitmap, degree: Int) : Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix,true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                BUTTON1 -> {
                    val imageBitmap = photoUri?.let {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.createSource(this.contentResolver, it)
                        } else {
                            @Suppress("DEPRECATION")
                            MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
                        }
                    }
                    binding.ivPre.setImageBitmap(imageBitmap?.let {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(it as ImageDecoder.Source)
                        } else {
                            @Suppress("DEPRECATION")
                            MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
                        }
                    })
                    Toast.makeText(this, photoUri?.path, Toast.LENGTH_LONG).show()
                    binding.answercameraCamerashootingCheckIb.setOnClickListener {
                        val intent=Intent(this, AnswerActivity::class.java)
                        intent.putExtra("photoUri", photoUri)
                        startActivity(intent)
                        finish()
                        Log.d("a1", photoUri.toString())

                    }
                }
            }
        }
    }


    //권한이 있는지 체크하는 메소드
    private fun checkPermissions(permissions: Array<String>, permissionsRequest: Int): Boolean {
        val permissionList : MutableList<String> = mutableListOf()
        for(permission in permissions){
            val result = ContextCompat.checkSelfPermission(this, permission)
            if(result != PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission)
            }
        }
        if(permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), PERMISSIONS_REQUEST)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(result in grantResults){
            if(result != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "권한 승인 부탁드립니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    //Bitmap 데이터를 비트맵 파일로 저장하는 함수
    // +newFileName() 메서드는 파일 이름 중복 제거를 위해 시간을 사용한 파일명을 생성하는 메서드
    private fun newJpgFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }
    private fun saveBitmapAsJPGFile(bitmap: Bitmap) {
        val path = File(filesDir, "image")
        if(!path.exists()){
            path.mkdirs()
        }
        val file = File(path, newJpgFileName())
        var imageFile: OutputStream? = null
        try{
            file.createNewFile()
            imageFile = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageFile)
            imageFile.close()
            Toast.makeText(this, file.absolutePath, Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            null
        }
    }
}