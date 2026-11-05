package com.example.amanturova_fatima_pi2_23_my_list

import android.app.Application
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.amanturova_fatima_pi2_23_my_list.databinding.FragmentAddBinding
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import kotlin.random.Random.Default.nextInt
import kotlin.text.insert


@AndroidEntryPoint
class AddFragment: Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var id_n = 1
    private lateinit var new_title: String
    private lateinit var new_text: String
    var newImage: ByteArray? = null
    private lateinit var bitmap: Bitmap
    private lateinit var contentResolver: ContentResolver
    private lateinit var photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root
        contentResolver = requireContext().contentResolver
        photoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                uri?.let {
                    try {
                        newImage = contentResolver.openInputStream(uri)?.readBytes().let {
                            UUID.randomUUID()
                            it
                        }
                        newImage?.let {
                            Toast.makeText(context, "Photo uploaded", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                bitmap = BitmapFactory.decodeByteArray(
                    newImage, 0,
                    newImage!!.size)

                binding.inputImage.setImageBitmap(bitmap)
            }
        binding.inputImage.setOnClickListener {
            photoPickerLauncher.launch(PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )) }
        return root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            if (binding.inputTitle.text.toString().isNotEmpty()
                && binding.inputText.text.toString().isNotEmpty()
                && newImage != null
            ) { val ntInt = nextInt(10000)
                id_n = ntInt
                new_title = binding.inputTitle.text.toString()
                new_text = binding.inputText.text.toString()
                val new_itemModel = ItemModel(
                    id_n, new_title, new_text, newImage as ByteArray
                )
                AppModule.provideRecordDatabase(requireActivity().applicationContext as Application
                ).recordDao().insert(new_itemModel)
                findNavController().navigate(R.id.action_addFragment2_to_homeFragment23)

            } else {
                showToast(requireActivity(), "No text or media was chosen") }

        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment2_to_homeFragment23)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}