# Почему не работает внешний пин-пад при использовании UposClientAidlInterface через меню "Продажа" Эвотора?

Вопрос по SberUpos для Эвотор 5i. Если запустить Activity как "Способ оплаты" из приложения "Продажа" Эвотора, то в этой Activity UposClientAidlInterface никак не реагирует на doSomething():<br/>
https://github.com/GrishaninViacheslav/sber_upos_outer_pin_pad_not_working/blob/master/app/src/main/java/ru/mars_groupe/sber_upos_example/MainActivity.kt

Видео-демонстрация проблемы: <br/>
https://youtu.be/Z2_GnAwJlD8<br/>
[![https://www.youtube.com/watch?v=Z2_GnAwJlD8](https://img.youtube.com/vi/Z2_GnAwJlD8/0.jpg)](https://www.youtube.com/watch?v=Z2_GnAwJlD8)


При этом всё работает корректно если запустить эту Activity  не из меню "Продажа" Эвотора, а из лаунчера: на видео 1:38-2:19

Также такой проблемы нет при работе со встроенным пин-падом ru.sberbank.uposnative: на видео 2:25-3:08

Также данная проблема отсутвтует если использовать UposVspClientAidlInterface вместо UposClientAidlInterface: на видео 3:08-4:01

Но у UposVspClientAidlInterface не скрывается Activity встроенного пин-пада: на видео 4:01-5:01


Почему не работает внешний пин-пад при использовании UposClientAidlInterface через меню "Продажа" Эвотора?
