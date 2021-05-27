package ru.rustisk.calcalc.util;

import ru.rustisk.calcalc.model.UserMeal;
import ru.rustisk.calcalc.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static final List<UserMeal> MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,10,0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,13,0), "Обед", 2000),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,19,0), "Ужин", 800),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,10,0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,13,0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,19,0), "Ужин", 500)
            );

    public static void main(String[] args) {


        List<UserMealWithExcess> filteredMealsWithExcesses = getFilteredMealsWithExcesses(MEAL_LIST, LocalTime.of(13, 0), LocalTime.of(19, 0), 2000);
        filteredMealsWithExcesses.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> getFilteredMealsWithExcesses(List<UserMeal> mealsList, LocalTime startTime, LocalTime endTime, int caloriesPerDay ){
        Map<LocalTime, Integer> caloriesSumByDate = mealsList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalTime(), Collectors.summingInt(UserMeal::getCalories)));
        return mealsList.stream()
                .filter(um->TimeUtil.isBetweenHalfOpen(um.getDateTime()
                        .toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        caloriesSumByDate.get(userMeal.getDateTime().toLocalTime())>caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExcess> getWithExcess(List<UserMeal> mealList, int caloriesPerDay) {
        return  getFilteredMealsWithExcesses(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }
}
