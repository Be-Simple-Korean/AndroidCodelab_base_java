# AndroidCodelab_base_java
안드로이드 코드랩 자바 기초

## TwoActivitys 
Intent, onSaveInstanceState() 활용한 화면 회전시 데이터 세팅<br>
Activity LifeCycle() 테스트

## Implicit_Intents
암시적 인텐트, ShareCompat.IntentBuilder를 활용한 앱 선택기 노출<br>
딥링크, Intent.createChooser를 활용한 앱 선택기 강제 노출

## SupportLibrary
getIdentifier()를 이용한 리소스의 id를 동적으로 가져오기<br>
지원 라이브러리를 이용하여 코딩하면 라이브러리를 통해 api레벨 구분 가능

## DroidCafe
android 기본 컴포넌트 활용<br>
Manifest에서 parent activity를 지정하여 화면이동하기

## RecyclerView
RecyclerView를 활용하여 데이터 리스트 만들기<br>
데이터가 추가되는 경우 notifyItemInsert로 추가되는 데이터만 bind()
-> 나의 경우는 그냥 notifyDataSetChanged()를 썼었는데 이 경우 전체 데이터가 재활용<br>
  
클릭리스너의 경우 뷰홀더에서 세팅해줌 
-> 나의 경우 bind에서 해줬지만 notify()등 데이터가 재활용될때마다 해당 리스너가 다시 세팅됨...

## ScoreKeeper
xml에서 공통 컴포넌트의 속성이 겹치는경우 스타일을 지정하여 한번에 처리<br>
shape, selector 리소스 사용<br>
다크모드 적용 및 적용 시 테마 변경이 적용되도록 recreate() 

## CardWithColor
ItemTouchHelper 
 * RecyclerView에서 스와이프, 드래그앤 드롭의 터치 액션 사용시 선언<br>


TypedArray 
  * strings.xml의 array 문자열 리스트 가져오기 = getResources().obtainTypedArray(id)<br>
  * View가 재사용 될 경우 .recycle()을 사용하고 가비지 컬렉션에서 제외시키기 위해 
 
 Collections.swap 
  * ArrayList의 데이터 스왑
  
화면의 orientation 별 폴더를 만들고 각 폴더에 value파일을 생성하여 액티비티에서 해당 파일의 데이터를 화면에 맞게 읽어오게 할 수 있음<br>
strings.xml 을 언어에 맞게 세팅

## PowerReceiver
동적 브로드캐스트 리시버, 로컬 브로드캐스트 리시버 등록 및 해제<br>
커스텀 리시버로 데이터 전달 및 커스텀 액션 전달<br>
리시버에서 액션에 따른 분기처리<br>
