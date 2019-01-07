package com.example.mhzhaog.myandroidsummary.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mhzhaog.myandroidsummary.R
import com.example.mhzhaog.myandroidsummary.adapter.RecyclerAdapter
import com.example.mhzhaog.myandroidsummary.bean.Child
import com.example.mhzhaog.myandroidsummary.interfaces.ItemDataClickListener

import kotlinx.android.synthetic.main.fragment_home_video.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeVideoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeVideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeVideoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home_video, container, false)
    }

    /**
     * 在Activity中,kotlin可以通过懒引用直接使用控件而不需要注册,
     * 但是在fragment中就不行!因为kotlin在Activity的onCreate方法执行过后就可以使用懒引用
     * 而fragment需要在onViewCreated方法执行后才能使用懒引用,
     * 看清楚:是onViewCreated,不是onCreateView就是说所有的处理控件显示啊,数据交互什么的,在onViewCreated里面处理就可以使用懒引用了
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        var adapter = RecyclerAdapter(activity)
        recyclerView.adapter = adapter

        var rootList = ArrayList<Child>()
        var streetList = ArrayList<Child>()
        var cameraList = ArrayList<Child>()
        for (i in 0..500){
            var child = Child()
            child.name = "摄像头$i"
            child.treeDepth = 3
            child.uuid = UUID.randomUUID().toString()
            cameraList.add(child)
        }
        for (i in 0..10){
            var child = Child()
            child.name = "街道$i"
            child.child = cameraList
            child.treeDepth = 2
            child.uuid = UUID.randomUUID().toString()
            streetList.add(child)
        }
        for (i in 0..10){
            var child = Child()
            child.name = "县$i"
            child.child = streetList
            child.treeDepth = 1
            child.uuid = UUID.randomUUID().toString()
            rootList.add(child)
        }
        adapter.addAll(rootList,0)

//        adapter.itemDataClickListener(object :ItemDataClickListener{
//            override fun invoke(itemDataClickListener: ItemDataClickListener) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onHideChildren(child: Child?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onPlayVideo(child: Child?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onExpandChildren(child: Child?) {
//                var position = adapter.getCurrentPosition(child?.uuid)
//                var childern: MutableList<Child>? = child?.child ?: return
//
//                adapter.addAll(childern,position+1)
//                child.child = childern
//
//            }
//        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeVideoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeVideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
