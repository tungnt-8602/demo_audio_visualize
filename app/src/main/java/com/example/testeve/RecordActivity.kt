package com.example.testeve

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ModalBottomSheet
import com.example.testeve.databinding.ActivityRecordBinding
import com.example.testeve.recorder.Recorder
import com.example.testeve.utils.checkAudioPermission
import com.example.testeve.utils.formatAsTime
import com.example.testeve.utils.getDrawableCompat
import kotlin.math.sqrt


class RecordActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRecordBinding
    private lateinit var recorder: Recorder

    companion object {
        private const val AUDIO_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

//        requestPermissions(
//            arrayOf(
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.RECORD_AUDIO,
//            ),
//            123
//        )
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
//                    124
//                )
//            }
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    125
//                )
//            }
//        } else {
//            if (!Environment.isExternalStorageManager()) {
//                try {
//                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//                    intent.addCategory("android.intent.category.DEFAULT")
//                    intent.setData(
//                        Uri.parse(
//                            String.format1(
//                                "package:%s",
//                                packageName
//                            )
//                        )
//                    )
//                    startActivityForResult(intent, FACE_REGISTER_REQ_PERMISSION_CODE)
//                } catch (e: Exception) {
//                    val intent = Intent()
//                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
//                    startActivityForResult(intent, FACE_REGISTER_REQ_PERMISSION_CODE)
//                }
//            } else {
//                val intent = Intent()
//            }
//        }

    }

    private fun initUI() = with(binding) {
        startRecord.setOnClickListener {
            if(checkAudioPermission(AUDIO_PERMISSION_REQUEST_CODE)){
                recorder.toggleRecording()
            }
        }

        recordingView.visualizer.ampNormalizer = { sqrt(it.toFloat()).toInt() }
        recordingView.playVisualizer.visibility = View.GONE
        recordingView.replayControl.visibility = View.GONE
    }

    private fun listenOnRecorderStates() = with(binding) {
        recorder = Recorder.getInstance(applicationContext).init().apply {
            onStart = {
                recordingView.root.visibility = View.VISIBLE
                startRecord.icon = getDrawableCompat(R.drawable.ic_close)
            }
            onStop = {
                recordingView.visualizer.clear()
                recordingView.root.visibility = View.GONE
                recordingView.timelineTextView.text = 0L.formatAsTime()
                startRecord.icon = getDrawableCompat(R.drawable.ic_record)
                val modalBottomSheet = RecordDialogFragment()
                modalBottomSheet.show(supportFragmentManager, RecordDialogFragment.TAG)
//                startActivity(Intent(this@MainActivity, PlayActivity::class.java))
            }
            onAmpListener = {
                runOnUiThread {
                    if (recorder.isRecording) {
                        recordingView.timelineTextView.text = recorder.getCurrentTime().formatAsTime()
                        recordingView.visualizer.addAmp(it, tickDuration)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        listenOnRecorderStates()
    }

    override fun onStop() {
        recorder.release()
        super.onStop()
    }
}