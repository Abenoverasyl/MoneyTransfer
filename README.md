# Request Processor TRANSFER MONEY

## Введение

Система для перевода денег между клиентами.

## Правила разработки
Разработка ведется по методологии feature branch flow. 

Правила наименования и комитов можно посмотреть [здесь](https://confluence.beeline.kz/display/ESB/Branch+naming+and+commits)
Основные принципы разработки [здесь](https://confluence.beeline.kz/pages/viewpage.action?pageId=67087394)

### Инструкция по работе с данным микросервисом

Запустите сервис.
Придерживайтесь принципов Single Responsibility Principle и Open/Closed Principle. Но в 
то же время оставляйте возможность переиспользовать свои интеграционные методы.

Основной функционал того или иного Api Type описывается в пакете `service.[actionName]`.