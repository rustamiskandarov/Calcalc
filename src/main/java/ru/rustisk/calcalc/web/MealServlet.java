package ru.rustisk.calcalc.web;


import org.slf4j.Logger;
import ru.rustisk.calcalc.model.UserMeal;
import ru.rustisk.calcalc.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setAttribute("mealsList", UserMealsUtil.getWithExcess(UserMealsUtil.MEAL_LIST, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
