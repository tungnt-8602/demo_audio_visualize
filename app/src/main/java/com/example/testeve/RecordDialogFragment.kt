package com.example.testeve

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.example.testeve.databinding.FragmentRecordDialogBinding
import com.example.testeve.player.AudioPlayer
import com.example.testeve.utils.formatAsTime
import com.example.testeve.utils.parseTimeToMillis
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.math.sqrt

class RecordDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentRecordDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var player: AudioPlayer
    private var currentTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        const val TAG = "ModalBottomSheet"
        const val SEEK_OVER_AMOUNT = 5000

        fun newInstance(itemCount: Int): RecordDialogFragment =
            RecordDialogFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        listenOnPlayerStates()
        initUI()
    }

    override fun onStop() {
        player.release()
        super.onStop()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() = with(binding) {
        replayView.playVisualizer.apply {
            ampNormalizer = { sqrt(it.toFloat()).toInt() }
            onStartSeeking = {
                player.pause()
            }
            onSeeking = { replayView.timelineTextView.text = it.formatAsTime() }
            onFinishedSeeking = { time, isPlayingBefore ->
                player.seekTo(time)
                if (isPlayingBefore) {
                    player.resume()
                }
            }
            onAnimateToPositionFinished = { time, isPlaying ->
                updateTime(time, isPlaying)
                player.seekTo(time)
            }
        }
        replayView.replayControl.setOnClickListener { player.togglePlay() }
        currentTime = replayView.timelineTextView.text.toString().parseTimeToMillis()!!
        for5.setOnClickListener {
            replayView.playVisualizer.seekOver(SEEK_OVER_AMOUNT)
        }
        back5.setOnClickListener {
            replayView.playVisualizer.seekOver(-SEEK_OVER_AMOUNT)
        }

        lifecycleScope.launchWhenCreated {
            val amps = player.loadAmps()
            replayView.playVisualizer.setWaveForm(amps, player.tickDuration)
        }
    }

    private fun listenOnPlayerStates() = with(binding) {
        player = AudioPlayer.getInstance(requireContext()).init().apply {
            onStart = { replayView.replayControl.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_pause_24, requireActivity().theme) }
            onStop = { replayView.replayControl.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_play_arrow_24, requireActivity().theme) }
            onPause = { replayView.replayControl.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_play_arrow_24, requireActivity().theme) }
            onResume = { replayView.replayControl.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_pause_24, requireActivity().theme) }
            onProgress = { time, isPlaying -> updateTime(time, isPlaying) }
        }
    }

    private fun updateTime(time: Long, isPlaying: Boolean) = with(binding) {
        replayView.timelineTextView.text = time.formatAsTime()
        replayView.playVisualizer.updateTime(time, isPlaying)
    }
}