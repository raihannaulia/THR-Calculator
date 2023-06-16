package org.d3if3036.assesment2mopro.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3036.assesment2mopro.R
import org.d3if3036.assesment2mopro.databinding.FragmentHitungBinding
import org.d3if3036.assesment2mopro.db.ThrDb
import org.d3if3036.assesment2mopro.model.HasilThr
import org.d3if3036.assesment2mopro.model.KategoriThr


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = ThrDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory) [HitungViewModel::class.java]

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungThr() }
        binding.shareButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilThr().observe(requireActivity(), { showResult(it) })
        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections.actionHitungFragmentToAboutFragment(

                )
            )
            viewModel.selesaiNavigasi()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_daftarUangFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hitungThr() {

        val gaji = binding.gajiEditText.text.toString()
        if (TextUtils.isEmpty(gaji)) {
            Toast.makeText(context, R.string.gaji_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tunjangan = binding.tunjanganEditText.text.toString()
        if (TextUtils.isEmpty(tunjangan)) {
            Toast.makeText(context, R.string.tunjangan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val lama_kerja = binding.lamaKerjaEditText.text.toString()
        if (TextUtils.isEmpty(lama_kerja)) {
            Toast.makeText(context, R.string.lamakerja_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.status_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungThr(
            gaji.toFloat(),
            tunjangan.toFloat(),
            lama_kerja.toFloat(),
            selectedId == R.id.seniorRadioButton
        )
    }


    private fun getStatusLabel(status: KategoriThr): String {
        val stringRes = when (status) {
            KategoriThr.JUNIOR -> R.string.junior
            KategoriThr.SENIOR -> R.string.senior
        }
        return getString(stringRes)
    }



    private fun showResult(result: HasilThr?) {
        if (result == null) return
        binding.thrTextView.text = getString(R.string.thr_x,(result.thr))
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val status = if (selectedId == R.id.seniorRadioButton)
            getString(R.string.senior)
        else
            getString(R.string.junior)
        val message = getString(R.string.bagikan_template,
            binding.gajiEditText.text,
            binding.tunjanganEditText.text,
            status,
            binding.thrTextView.text,
            binding.thrTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

}