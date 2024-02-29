const column = document.getElementsByClassName('column-content');
for(let c of column) {
    /* 공지 게시글 클릭시 */
    c.addEventListener('click', e => {
      const columnNo = e.target.parentElement.getAttribute("value");
      document.location.href="/verses/columnDetail/"+columnNo
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("columnModify")!=null){
  document.getElementById("columnModify").addEventListener("click", e=>{
    const columnNo = document.getElementById("columnModify").getAttribute("value")
    document.location.href="/column/modify/"+columnNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("columnDelete") != null) {
  document.getElementById("columnDelete").addEventListener("click", e=>{
    const columnNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/column/delete?columnNo="+columnNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/verses/column"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}