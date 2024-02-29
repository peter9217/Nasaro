const sharing = document.getElementsByClassName('sharing-content');
for(let s of sharing) {
    /* 공지 게시글 클릭시 */
    s.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/sharingDetail/"+no
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("sharingModify")!=null){
  document.getElementById("sharingModify").addEventListener("click", e=>{
    const sharingNo = document.getElementById("sharingModify").getAttribute("value")
    document.location.href="/sharing/modify/"+sharingNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("sharingDelete") != null) {
  document.getElementById("sharingDelete").addEventListener("click", e=>{
    const sharingNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/sharing/delete?sharingNo="+sharingNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/community/sharing"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}