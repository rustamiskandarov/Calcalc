<%--
  Created by IntelliJ IDEA.
  User: Rust
  Date: 30.05.2021
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
    <style>
        form {
            /* Расположим форму по центру страницы */
            margin: 0 auto;
            width: 400px;
            /* Определим контур формы */
            padding: 1em;
            border: 1px solid #CCC;
            border-radius: 1em;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        form li + li {
            margin-top: 1em;
            list-style: none;
        }

        label {
            /* Определим размер и выравнивание */
            display: inline-block;
            width: 90px;
            text-align: right;
        }

        input,
        textarea {
            /* Убедимся, что все поля имеют одинаковые настройки шрифта
               По умолчанию в textarea используется моноширинный шрифт */
            font: 1em sans-serif;

            /* Определим размер полей */
            width: 300px;
            box-sizing: border-box;

            /* Стилизуем границы полей */
            border: 1px solid #999;
        }

        input:focus,
        textarea:focus {
            /* Дополнительная подсветка для элементов в фокусе */
            border-color: #000;
        }

        textarea {
            /* Выравним многострочные текстовые поля с их текстами-подсказками */
            vertical-align: top;

            /* Предоставим пространство для ввода текста */
            height: 5em;
        }

        .button {
            /* Выравним кнопки с их текстами-подсказками */
            padding-left: 90px; /* same size as the label elements */
        }

        button {
            /* Этот дополнительный внешний отступ примерно равен расстоянию
               между текстами-подсказками и текстовыми полями */
            margin-left: .5em;
        }
    </style>
</head>
<body>
<form action="" method="post">
    <ul>
        <li>
            <label for="name">Name:</label>
            <input type="text" id="name" name="user_name">
        </li>
        <li>
            <label for="mail">E-mail:</label>
            <input type="email" id="mail" name="user_mail">
        </li>
        <li>
            <label for="msg">Message:</label>
            <textarea id="msg" name="user_message"></textarea>
        </li>
        <li class="button">
            <button type="submit">Send your message</button>
        </li>
    </ul>
</form>
</body>
</html>
