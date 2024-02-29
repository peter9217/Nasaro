const reference = document.getElementsByClassName('reference-content');
for(let r of reference) {
    /* 공지 게시글 클릭시 */
    r.addEventListener('click', e => {
      const reference = e.target.parentElement.getAttribute("value");
      document.location.href="/verses/referenceDetail/"+reference
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("referenceModify")!=null){
  document.getElementById("referenceModify").addEventListener("click", e=>{
    const referenceNo = document.getElementById("referenceModify").getAttribute("value")
    document.location.href="/reference/modify/"+referenceNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("referenceDelete") != null) {
  document.getElementById("referenceDelete").addEventListener("click", e=>{
    const referenceNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/reference/delete?referenceNo="+referenceNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/verses/reference"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}