const column = document.getElementsByClassName('column-content');
for(let c of column) {
    /* 공지 게시글 클릭시 */
    c.addEventListener('click', e => {
      const columnNo = e.target.parentElement.getAttribute("value");
      if (columnNo!=null) {
        document.location.href="/verses/columnDetail/"+columnNo
        
      }
    });
  
};

//  컬럼 수정하기 버튼
if(document.getElementById("columnModify") != null) {
  document.getElementById("columnModify").addEventListener("click", e => {
    e.preventDefault(); // 기존의 클릭 동작을 취소함
    
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const columnNo = document.getElementById("columnModify").getAttribute("value")
    
    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/column/modify/' + columnNo);

    // 토큰을 담을 hidden input 생성
    const tokenInput = document.createElement('input');
    tokenInput.setAttribute('type', 'hidden');
    tokenInput.setAttribute('name', name);
    tokenInput.setAttribute('value', token);

    // 폼에 hidden input 추가
    form.appendChild(tokenInput);

    // 폼을 body에 추가하고 submit
    document.body.appendChild(form);
    form.submit();
  });
}




/* 컬럼 삭제 버튼 */
if (document.getElementById("columnDelete") != null) {
  document.getElementById("columnDelete").addEventListener("click", e=>{
    const columnNo = e.target.value;
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    
    if (confirm("삭제하시겠습니까?")) {
        fetch("/column/delete", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              [csrfHeader]: csrfToken
            },
            body: JSON.stringify(columnNo)
        })
        .then(response => response.text())
        .then(() => {
            setTimeout(function() {
                document.location.href = "/verses/column";
            }, 500);
        })
        .catch(e => {
            console.log(e);
        });
    }
  })
}