package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.*

class NutritionView(context: Context, nutrition: Nutrition) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var calorieView: TextView ?= null
    private var totalFatView: TextView ?= null
    private var saturatedFatView: TextView ?= null
    private var transFatView: TextView ?= null
    private var dietaryFiberView: TextView ?= null
    private var sugarView: TextView ?= null
    private var proteinView: TextView ?= null
    private var cholesterolView: TextView ?= null
    private var sodiumView: TextView ?= null
    private var vitaminAView: TextView ?= null
    private var vitaminCView: TextView ?= null
    private var vitaminDView: TextView ?= null
    private var calciumView: TextView ?= null
    private var ironView: TextView ?= null
    private var unstaturatedFatView: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.nutrition_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        calorieView = findViewById(R.id.calories)
        totalFatView = findViewById(R.id.total_fat)
        saturatedFatView = findViewById(R.id.saturated_fat)
        transFatView = findViewById(R.id.trans_fat)
        dietaryFiberView = findViewById(R.id.dietary_fiber)
        sugarView = findViewById(R.id.sugar)
        proteinView = findViewById(R.id.protein)
        cholesterolView = findViewById(R.id.cholesterol)
        sodiumView = findViewById(R.id.sodium)
        vitaminAView = findViewById(R.id.vitamin_a)
        vitaminCView = findViewById(R.id.vitamin_c)
        vitaminDView = findViewById(R.id.vitamin_d)
        calciumView = findViewById(R.id.calcium)
        ironView = findViewById(R.id.iron)
        unstaturatedFatView = findViewById(R.id.unstaturatedFat)

        startTime?.text = "start time : " + HspUtils.getDateTime(nutrition.startTime)
        calorieView?.text = "calories : " + nutrition.calories +  " grams"
        totalFatView?.text = "total Fat : " + nutrition.totalFat +  " grams"
        saturatedFatView?.text = "saturated Fat : " + nutrition.saturatedFat +  " grams"
        transFatView?.text = "trans Fat : " + nutrition.transFat +  " grams"
        dietaryFiberView?.text = "dietary Fiber : " + nutrition.dietaryFiber +  " grams"
        sugarView?.text = "sugar : " + nutrition.sugar +  " grams"
        proteinView?.text = "protein : " + nutrition.protein +  " grams"
        cholesterolView?.text = "cholesterol : " + nutrition.cholesterol +  " grams"
        sodiumView?.text = "sodium : " + nutrition.sodium +  " grams"
        vitaminAView?.text = "vitamin A : " + nutrition.vitaminA +  " grams"
        vitaminCView?.text = "vitamin C : " + nutrition.vitaminC +  " grams"
        vitaminDView?.text = "vitamin D : " + nutrition.vitaminD +  " grams"
        calciumView?.text = "calcium : " + nutrition.calcium +  " grams"
        ironView?.text = "iron : " + nutrition.iron +  " grams"
        packageName?.text = "package name : " + nutrition.pkgName
    }



}