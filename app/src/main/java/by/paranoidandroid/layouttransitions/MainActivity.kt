package by.paranoidandroid.layouttransitions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAnimate = findViewById<Button>(R.id.btn_animate)
        val btnSearchActivity = findViewById<Button>(R.id.btn_search_activity)
        val btnSharedAnimationActivity = findViewById<Button>(R.id.btn_shared_animation_activity)
        val textView = findViewById<TextView>(R.id.title)
        val imageView = findViewById<ImageView>(R.id.iv_image)

        btnAnimate.setOnClickListener {
            animateTransition()
        }
        btnSearchActivity.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        btnSharedAnimationActivity.setOnClickListener {
            val intent = Intent(this, SharedAnimationActivity::class.java)

            /*
            * From docs:
            * If you try to resize a TextView with an animation,
            * the text will pop to a new location before the object has completely resized.
            * To avoid this problem, do not animate the resizing of views that contain text.
            */
            val textPair = Pair.create(textView as View, getString(R.string.text_transition_name))
            val imagePair = Pair.create(imageView as View, getString(R.string.image_transition_name))

            val options: Bundle? = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this, textPair, imagePair)
                    .toBundle()
            startActivity(intent, options)
        }
    }

    private fun animateTransition() {
        val sceneRoot: ViewGroup = findViewById(R.id.scene_root)
        //val sceneA: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_a, this)
        val sceneB: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_b, this)

        val fadeTransition: Transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.transition_set_auto)

        TransitionManager.go(sceneB, fadeTransition)
    }
}