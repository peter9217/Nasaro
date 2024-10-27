const reference = document.getElementsByClassName('reference-content');
for(let r of reference) {
    /* 공지 게시글 클릭시 */
    r.addEventListener('click', e => {
      const reference = e.target.parentElement.getAttribute("value");
      if (reference!=null) {
        document.location.href="/verses/referenceDetail/"+reference
      }
    });
  
};

//  레퍼 수정하기 버튼

if(document.getElementById("referenceModify") != null) {
  document.getElementById("referenceModify").addEventListener("click", e => {
    e.preventDefault(); // 기존의 클릭 동작을 취소함
    
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const referenceNo = document.getElementById("referenceModify").getAttribute("value")

    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/reference/modify/' + referenceNo);

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





/* 레퍼 삭제 버튼 */
if (document.getElementById("referenceDelete") != null) {
  document.getElementById("referenceDelete").addEventListener("click", e=>{
    const referenceNo = e.target.value;
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    
    if (confirm("삭제하시겠습니까?")) {
        fetch("/reference/delete", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              [csrfHeader]: csrfToken
            },
            body: JSON.stringify(referenceNo)
        })
        .then(response => response.text())
        .then(() => {
            setTimeout(function() {
                document.location.href = "/verses/reference";
            }, 500);
        })
        .catch(e => {
            console.log(e);
        });
    }
  })
}