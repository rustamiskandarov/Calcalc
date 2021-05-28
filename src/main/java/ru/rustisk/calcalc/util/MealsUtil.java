package ru.rustisk.calcalc.util;

import ru.rustisk.calcalc.model.Meal;
import ru.rustisk.calcalc.model.MealTo;
import ru.rustisk.calcalc.model.MealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<Meal> MEAL_LIST = Arrays.asList(
            new Meal(LocalDateTime.of(2021, Month.MAY, 29, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2021, Month.MAY, 29, 13, 0), "Обед", 2000),
            new Meal(LocalDateTime.of(2021, Month.MAY, 29, 19, 0), "Ужин", 800),
            new Meal(LocalDateTime.of(2021, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2021, Month.MAY, 30, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2021, Month.MAY, 30, 19, 0), "Ужин", 500)
    );

    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
        return getFiltered(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredTos(List<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return getFiltered(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
    }

    private static List<MealTo> getFiltered(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                        //                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );
        return meals.stream()
                    .filter(filter)
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(null, meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<MealWithExcess> getFilteredMealsWithExcesses(List<Meal> mealsList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalTime, Integer> caloriesSumByDate = mealsList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalTime(), Collectors.summingInt(ru.rustisk.calcalc.model.Meal::getCalories)));
        return mealsList.stream()
                .filter(um -> TimeUtil.isBetweenHalfOpen(um.getDateTime()
                        .toLocalTime(), startTime, endTime))
                .map(meal -> new MealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesSumByDate.get(meal.getDateTime().toLocalTime()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealWithExcess> getWithExcess(List<Meal> mealList, int caloriesPerDay) {
        return getFilteredMealsWithExcesses(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }
}
