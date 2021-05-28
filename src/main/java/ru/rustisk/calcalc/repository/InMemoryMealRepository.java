package ru.rustisk.calcalc.repository;

import ru.rustisk.calcalc.model.Meal;
import ru.rustisk.calcalc.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repo = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()){
            meal.setId(counter.incrementAndGet());
            repo.put(meal.getId(), meal);
            return meal;
        }
        return repo.computeIfPresent(meal.getId(), (id, oldMeal)->meal);
    }

    @Override
    public boolean delete(int id) {
        return repo.remove(id) != null;
    }

    @Override
    public Meal get(int id){
        return repo.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repo.values();
    }
}
