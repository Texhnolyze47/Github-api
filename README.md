## Spring boot con la API de Github

Este es un proyecto que usa spring boot para crear un apiRest que pueda
consumir el api de Github.

## Endpoints
Esto se puede consultar mediante PostMan o
en caso tener la version ultimate de IDEA Intellij
mediante el Http Client que integra Intellij.

```
http://localhost:8080/users/All
```
Este endpoint regresa una lista con los nombres de usuarios y sus ids

```json
[
    "Member{login='ErickADSD', id=107226830}",
    "Member{login='JosafatJimenezB', id=88176494}",
    "Member{login='memo5586482734', id=107533458}",
    "Member{login='phdpalma', id=70855630}",
    "Member{login='Texhnolyze47', id=47402911}"
]
```

Este endpoint se usa mandar de manera automatica invitaciones a quienes 
ponga su correo
```
http://localhost:8080/users/add

```

```json
{
    "org": "Escihu-Wizards",
    "email": "gabenewllson@gmail.com",
    "role": "direct_member"
}
```
