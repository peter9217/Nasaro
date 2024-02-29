const nations = document.getElementsByClassName('nations-content');
for(let n of nations) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/pray/nationsDetail/"+no
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("nationsModify")!=null){
  document.getElementById("nationsModify").addEventListener("click", e=>{
    const nationsNo = document.getElementById("nationsModify").getAttribute("value")
    document.location.href="/nations/modify/"+nationsNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("nationsDelete") != null) {
  document.getElementById("nationsDelete").addEventListener("click", e=>{
    const nationsNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/nations/delete?nationsNo="+nationsNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/pray/nations"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}