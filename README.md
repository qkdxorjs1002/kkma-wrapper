# KkmaWrapper

> Command-line wrapper for kkma jar

## 사용법

```plain
>>> Usage <<<
  > java -jar kkma-wrapper.jar <Options> <Input Text 1> <Input Text 2> <Input Text 3> ...

>>> Options <<<
  --help			 help.
  --log				 enable kkma log output.
  --b64				 enable base64 input/output.
  --disable-print-output	 disable output printing.
```

## 예시

### 다중 문장 입력
```bash
> java -jar kkma-2.1-wrapper-1.0-SNAPSHOT.jar "안녕하세요" " 감사합니다." "잘가요." "누구보다 빠르게"
[["안녕/NNG","하/XSV","세요/EFN"],["감사/NNG","하/XSV","ㅂ니다/EFN","./SF"],["잘/MAG","가/VV","아요/EFN","./SF"],["누구/NP","보다/JKM","빠르/VA","게/ECD"]]
```

### BASE64 인코딩 입/출력
```bash
> java -jar kkma-2.1-wrapper-1.0-SNAPSHOT.jar --b64 "W1si7JWI64WVL05ORyIsIu2VmOyEuC9OTkciLCLsmpQvSlgiLCIsL1NQIiwi67CY6rCRL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLsnpgvTUFHIiwi7J6IL1ZBIiwi7Ja07JqUL0VGTiIsIi4vU0YiLCLri6Tsi5wvTUFHIiwi66eM64KYL1ZWIiwi7JWE7JqUL0VGTiIsIi4vU0YiXV0="
W1siW1svU1ciLCJcIi9TUyIsIuyViOuFlS9OTkciLCIvL1NQIiwiTk5HL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCLtlZjshLgvTk5HIiwiLy9TUCIsIk5ORy9PTCIsIlwiL1NTIiwiLC9TUCIsIlwiL1NTIiwi7JqUL0pDIiwiLy9TUCIsIkpYL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCIsL1NQIiwiLy9TUCIsIlNQL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCLrsJjqsJEvVlYiLCIvL1NQIiwiVkEvT0wiLCJcIi9TUyIsIiwvU1AiLCJcIi9TUyIsIuyWtC9WViIsIuyWtOyalC9FRk4iLCIvL1NQIiwiRUZOL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCIuL1NGIiwiLy9TUCIsIlNGL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCLsnpgvTUFHIiwiLy9TUCIsIk1BRy9PTCIsIlwiL1NTIiwiLC9TUCIsIlwiL1NTIiwi7J6IL1ZYQSIsIi8vU1AiLCJWQS9PTCIsIlwiL1NTIiwiLC9TUCIsIlwiL1NTIiwi7Ja0L1ZWIiwi7Ja07JqUL0VGTiIsIi8vU1AiLCJFRk4vT0wiLCJcIi9TUyIsIiwvU1AiLCJcIi9TUyIsIi4vU0YiLCIvL1NQIiwiU0YvT0wiLCJcIi9TUyIsIiwvU1AiLCJcIi9TUyIsIuuLpOyLnC9NQUciLCIvL1NQIiwiTUFHL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCLrp4zrgpgvVlYiLCLslYQvRUNTIiwiLy9TUCIsIlZWL09MIiwiXCIvU1MiLCIsL1NQIiwiXCIvU1MiLCLslYQvVlYiLCLslYTsmpQvRUZOIiwiLy9TUCIsIkVGTi9PTCIsIlwiL1NTIiwiLC9TUCIsIlwiL1NTIiwiLi9TRiIsIi8vU1AiLCJTRi9PTCIsIlwiL1NTIiwiXV0vU1ciXV0=
```

### 로그 출력
```bash
> java -jar kkma-2.1-wrapper-1.0-SNAPSHOT.jar --log "안녕하세요, 감사합니다."
Loading /dic/00nng.dic
Prob Dic Loading!
...
Loaded Item 204148
DO LOGGING!!
[["안녕/NNG","하세/NNG","요/JX",",/SP","감사/NNG","하/XSV","ㅂ니다/EFN","./SF"]]
```

## LICENSE

본 저장소의 라이브러리 일부는 꼬꼬마 프로젝트의 저작권을 따릅니다.

[꼬꼬마 프로젝트](http://kkma.snu.ac.kr)

GPL-2.0 [LICENSE](LICENSE)