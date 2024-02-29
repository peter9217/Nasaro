const notice = document.getElementsByClassName('notice-content');
for(let n of notice) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/noticeDetail/"+no
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("noticeModify")!=null){
  document.getElementById("noticeModify").addEventListener("click", e=>{
    const noticeNo = document.getElementById("noticeModify").getAttribute("value")
    document.location.href="/notice/modify/"+noticeNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("noticeDelete") != null) {
  document.getElementById("noticeDelete").addEventListener("click", e=>{
    const noticeNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/notice/delete?noticeNo="+noticeNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/community/notice"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}