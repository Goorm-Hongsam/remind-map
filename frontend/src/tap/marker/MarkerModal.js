import React, { useEffect, useRef } from 'react';

const MarkerModal = ({ data, onClose }) => {
  const modalRef = useRef();
  useEffect(() => {
    const handleClickOutside = event => {
      if (modalRef.current && !modalRef.current.contains(event.target)) {
        onClose(); // 모달 외부 클릭시 onClose 함수 호출
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [onClose]);

  return (
    <div
      ref={modalRef}
      style={{
        position: 'absolute',
        top: '8%',
        left: '342px',
        width: '200px',
        height: '80%',
        backgroundColor: 'white',
        padding: '20px',
        zIndex: 1000,
      }}
    >
      <h2>{data.place_name}</h2>
      <p>{data.address_name}</p>
      <button onClick={onClose}>닫기</button>
    </div>
  );
};

export default MarkerModal;
