# Запуск програми
Є три головних класи **Main.class**, **JsonThreadStatistic.class** та **XMLWriterTest.class**.
###
**Main:**

Основний клас з консольним запуском. <br>
Приклади запуску `<path> 1 <STATISTIC_ENUM>` або `<path> 2 <FILTER_ENUM> value`
*     ./src/main/resources/json/ 1 STATISTIC_BY_GENRE
*     ./src/main/resources/json/ 2 FILTER_BY_YEAR_LESS 200
*     ./src/main/resources/json/ 2 FILTER_BY_DIRECTOR Robert
###
**JsonThreadStatistic:**

Клас завдяки якому ми виконуємо пункт 5. <br>
Приклад запуску:

Вивід:
1. Time taken with 1 threads: 1.0565 milliseconds
2. Time taken with 2 threads: 0.2712 milliseconds
3. Time taken with 4 threads: 0.3972 milliseconds
4. Time taken with 8 threads: 0.6802 milliseconds
###
**XMLWriterTest:**

Запуск усіх методів(створення файлів на усі запити), перевірка на считування невалідної інформації та на наявність створених файлів.

# Опис сутностей
**Movie**
* String *title* - назва
* Integer *year* - рік
* List(String) *genres* - жанри
* Director *director* - директор

**Director**
* String *name* - ім`я
* String *surname* - прізвище

# Приклад вхідних даних та вихідних файлів
[Json файли](src/main/resources/json) на вхід.
```
[
  {
    "title": "Inception",
    "year": 2010,
    "genres": ["Action", "Adventure", "Sci-Fi"],
    "director": "Christopher Nolan"
  }
]
```
[XML файли](src/main/resources/xml) на вихід.
```
<statistics>
  <item>
    <value>Drama</value>
    <count>28304</count>
  </item>
</statistics>
```
# Опис результатів експерименту з кількістю потоків
```
Apr 18, 2024 4:20:57 PM com.github.project.reader.JSONThreadStatistic parseFilesWithThreadPool
INFO: Time taken with 1 threads: 1.0565 milliseconds
Apr 18, 2024 4:20:58 PM com.github.project.reader.JSONThreadStatistic parseFilesWithThreadPool
INFO: Time taken with 2 threads: 0.2712 milliseconds
Apr 18, 2024 4:20:58 PM com.github.project.reader.JSONThreadStatistic parseFilesWithThreadPool
INFO: Time taken with 4 threads: 0.3972 milliseconds
Apr 18, 2024 4:20:58 PM com.github.project.reader.JSONThreadStatistic parseFilesWithThreadPool
INFO: Time taken with 8 threads: 0.6802 milliseconds
```
У результатах експериментів з кількістю потоків видно, що час, необхідний для обробки JSON-файлів, зменшується при збільшенні кількості потоків до певного моменту. 
Однак, після досягнення певного порогу кількості потоків, подальше збільшення не призводить до подальшого значного зменшення часу обробки. 
Це може бути пов'язано з обмеженим кількістю фізичних ядер процесора, а також з накладними витратами на управління потоками.

