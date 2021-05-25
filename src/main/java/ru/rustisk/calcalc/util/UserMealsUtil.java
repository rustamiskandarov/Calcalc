package ru.rustisk.calcalc.util;

import ru.rustisk.calcalc.model.UserMeal;
import ru.rustisk.calcalc.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,10,0), "Завтрак", 5000),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,13,0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 29,19,0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,10,0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,13,0), "Обед", 5000),
            new UserMeal(LocalDateTime.of(2021, Month.MAY, 30,19,0), "Ужин", 500)
        );
        System.out.println(getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealsList, LocalTime localTimeStart, LocalTime localTimeEnd, int calories ){
        return mealsList.stream()
                .filter(userMeal -> userMeal.getCalories() > calories)
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true))
                .collect(Collectors.toList());
    }
}
