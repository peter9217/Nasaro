const verses = document.getElementsByClassName('verses-content');
for(let v of verses) {
    /* 공지 게시글 클릭시 */
    v.addEventListener('click', e => {
      const versesNo = e.target.parentElement.getAttribute("value");
      document.location.href="/verses/versesDetail/"+versesNo
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("versesModify")!=null){
  document.getElementById("versesModify").addEventListener("click", e=>{
    const versesNo = document.getElementById("versesModify").getAttribute("value")
    document.location.href="/verses/modify/"+versesNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("versesDelete") != null) {
  document.getElementById("versesDelete").addEventListener("click", e=>{
    const versesNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/verses/delete?versesNo="+versesNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/verses/verses"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}
