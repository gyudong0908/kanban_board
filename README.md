# kanbanBoard(5인 프로젝트)
- 역할: backend
## Design ☄️
![image](https://github.com/gyudong0908/kanban_board/assets/121427661/393a25e7-46e9-4819-84f7-36a1d752f120)
## ERD
![image](https://github.com/gyudong0908/kanban_board/assets/121427661/31263d29-7ffc-4ffd-9d9e-9ecbd2293c10)
## Controller
![image](https://github.com/gyudong0908/kanban_board/assets/121427661/8d2bf78f-36ee-4e4c-85d0-4ffd9ec8e22c)
## Service
![image](https://github.com/gyudong0908/kanban_board/assets/121427661/d9413497-9f04-4687-8080-0a7ed663fa4a)
## 어려웠던 점
1. 팀원 모두 깃을 처음 사용하여 commit이 꼬이고 진행이 어려웠었다. 이 부분을 팀원 모두가 모여 룰을 만들고 지키며 해결해 나갈 수 있었다.(밑 사진은 처음 사용할 당시의 graph이다 파랑색이 main)
<img src = "https://github.com/gyudong0908/kanban_board/assets/121427661/ffe1b39e-8688-4470-9a06-eaf5e80ba3c8" height="100px"></img>
2. 데이터를 보내줄때, 필요하지 않은 데이터를 제외하고 보내주는 것이 어려웠다. 예를 들면 같은 board데이터여도 mainpage에서 요구하는 정보와 다른 page에서 요구하는 정보의 필요한 부분이 다를때, board에 모든 데이터를 보내주는 것이 아닌 필요한 데이터만 주는 방법을 고민했다. 해결방안은 별도의 reponse용 DTO를 따로 만들어주는 방법으로 해결 하였지만 해당 방법을 사용하면 DTO의 갯수가 매우 많아져 좋은 해결방안은 아닌것 같다는 생각이 들었다.
3. 공부를 하다 보면 service나 handler에서 interface를 만들고 해당 interface를 상속받아 구현하는 예제들을 종종 확인 하였다. 처음엔 따라서 만들었지만 구조적으로 더 복잡하다 생각하여 interface를 삭제하고 만들게 되었다. 그렇게 개발하면서 유지보수의 어려움을 느꼈다. 코드를 수정할 때, 정해진 뼈대가 없어서 수정할 때마다 구조가 변경되고 그에 따라 controller도 수정해주고 의존되어있는 모든 코드도 같이 수정해 줘야 했기 때문이다. 따라서 사람들이 interface를 쓰는 이유를 몸소 깨달을 수 있었고 또한 초반 설계에 대한 중요성을 뼈져리게 느낄 수 있었다.
## 배웠던 점
1. spring boot와 mysql, jpa의 기본적인 사용방법
2. git 사용법과 협업 경험
