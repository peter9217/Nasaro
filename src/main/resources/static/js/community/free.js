const free = document.getElementsByClassName('free-content');
for(let n of free) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      if (no!=null) {
        document.location.href="/community/freeDetail/"+no
      }
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("freeModify")!=null){
  document.getElementById("freeModify").addEventListener("click", e=>{
    const freeNo = document.getElementById("freeModify").getAttribute("value")
    document.location.href="/free/modify/"+freeNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("freeDelete") != null) {
  document.getElementById("freeDelete").addEventListener("click", e=>{
    const freeNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/free/delete", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          [csrfHeader]: csrfToken
        },
        body: JSON.stringify(freeNo), // JSON 형식으로 데이터 변환
    })
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/community/free"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}