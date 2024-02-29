const family = document.getElementsByClassName('family-content');
for(let f of family) {
    /* 공지 게시글 클릭시 */
    f.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/pray/familyDetail/"+no
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("familyModify")!=null){
  document.getElementById("familyModify").addEventListener("click", e=>{
    const familyNo = document.getElementById("familyModify").getAttribute("value")
    document.location.href="/family/modify/"+familyNo
  })
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("familyDelete") != null) {
  document.getElementById("familyDelete").addEventListener("click", e=>{
    const familyNo = e.target.value;
    if (confirm("삭제하시겠습니까?")) {
      fetch("/family/delete?familyNo="+familyNo)  
      .then(response => response.text()) 
      .then(() => {
      }) 
      .catch (e => { console.log(e)}); 
  
      setTimeout(function(){
        document.location.href="/pray/family"
      },500);
    } else {
      e.preventDefault()
    }
    
  })
}