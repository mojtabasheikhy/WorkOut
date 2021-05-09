package com.example.a7minutes_workout

import com.example.a7minutes_workout.model.Exercisemodel

object Const_Exercise {
    fun exercise_list(): ArrayList<Exercisemodel> {
        var exercise_list = ArrayList<Exercisemodel>()


        var jumping = Exercisemodel(1,"Push up", R.raw.pushups, false, false)
        exercise_list.add(jumping)

        var crunch = Exercisemodel(2,"Crunches", R.raw.abs_crunches, false, false)
        exercise_list.add(crunch)

        var lunge = Exercisemodel(3,"Boat Hold leg Flutters", R.raw.boat_hold_leg_flutters, false, false)
        exercise_list.add(lunge)

        var plank = Exercisemodel(4,"Bulgarian Split Squat jump Right",R.raw.bulgarian_split_squat_jump_right, false, false)
        exercise_list.add(plank)

        var push_up = Exercisemodel(5,"butt kicks", R.raw.butt_kicks, false, false)
        exercise_list.add(push_up)

        var wall_sit = Exercisemodel(6,"wide push uo", R.raw.wide_push_up, false, false)
        exercise_list.add(wall_sit)

        var step_up = Exercisemodel(7,"Men doing rope exercise", R.raw.men_doing_rope_exercise, false, false)
        exercise_list.add(step_up)

        var abdominal = Exercisemodel(8,"Spiderman push up",R.raw.spiderman_push_up, false, false)
        exercise_list.add(abdominal)

        var running = Exercisemodel(9,"Healthy lifestyle exercise", R.raw.healthy_lifestyle_exercise ,false, false)
        exercise_list.add(running)

        var squat = Exercisemodel(10,"Jumping jack", R.raw.jumping_jack, false, false)
        exercise_list.add(squat)

        return exercise_list
    }
}