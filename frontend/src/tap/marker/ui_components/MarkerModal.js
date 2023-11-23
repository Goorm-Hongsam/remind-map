import React, { useEffect, useRef, useState } from 'react';
import Styles from './MarkerModal.module.css';
import Datepicker from 'react-tailwindcss-datepicker';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination } from 'swiper/modules';
import defaultImg from '../../../Image/미디어선택.png';
import { MdCheckBox } from 'react-icons/md';

const MarkerModal = ({ data, onClose }) => {
  const modalRef = useRef();
  const [selectedDate, setSelectedDate] = useState({
    startDate: null,
    endDate: null,
  });
  const [formData, setFormData] = useState({
    title: '',
    memo: '',
    images: [],
    location: {},
    visible: '',
    wentDate: '',
  });
  const [images, setImages] = useState([]);
  const [isChecked, setIsChecked] = useState(false);
  const handleCheckbox = () => {
    setIsChecked(!isChecked);
  };
  useEffect(() => {
    setFormData(prevState => ({
      ...prevState,
      visible: isChecked ? 1 : 0,
    }));
  }, [isChecked]);
  useEffect(() => {
    const handleClickOutside = event => {
      if (modalRef.current && !modalRef.current.contains(event.target)) {
        onClose();
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [onClose]);

  const handleDateChange = date => {
    setSelectedDate(date);
  };
  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
      location: {
        ...prevState.location,
        latitude: data.position.La,
        longitude: data.position.Ma,
      },
      wentDate: selectedDate.startDate,
    }));
  };
  const handleSubmit = e => {
    e.preventDefault();
    const completeFormData = {
      ...formData,
      images,
    };
    onClose();
    console.log(completeFormData);
  };
  const handleImageUpload = event => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = e => {
        setImages(oldImages => [...oldImages, e.target.result]);
      };
      reader.readAsDataURL(file);
    }
  };
  return (
    <div className={Styles.MarkerpostingModal}>
      <div className="modalContainer">
        <button className={Styles.closeButton} onClick={onClose}>
          ×
        </button>
        <div className={Styles.titleBar}>
          <h2>마커 생성</h2>
        </div>
        <input
          type="file"
          onChange={handleImageUpload}
          style={{ display: 'none' }}
          id="image-upload"
        />
        <Swiper modules={[Navigation, Pagination]} navigation pagination={{ clickable: true }}>
          {images.length === 0 ? (
            <SwiperSlide>
              <label htmlFor="image-upload" className={Styles.uploadButton}>
                <img src={defaultImg} alt="default" />
              </label>
            </SwiperSlide>
          ) : (
            images.map((image, index) => (
              <SwiperSlide key={index}>
                <img src={image} alt={`Uploaded-${index}`} />
              </SwiperSlide>
            ))
          )}
        </Swiper>
        <form onSubmit={handleSubmit}>
          <label className={Styles.inputLabel}>마커 제목</label>
          <input
            className={Styles.inputField}
            type="text"
            name="title"
            value={formData.title}
            onChange={handleChange}
          />
          <label className={Styles.inputLabel}>날짜</label>
          <Datepicker
            inputClassName="w-full p-2"
            containerClassName={`${Styles.groupTapItem} border rounded-sm`}
            toggleClassName="hidden"
            primaryColor="purple"
            asSingle={true}
            value={selectedDate}
            onChange={handleDateChange}
            placeholder="YYYY-MM-DD"
          />
          <label className={Styles.inputLabel}>추가메모</label>
          <textarea
            className={Styles.textAreaField}
            name="memo"
            value={formData.memo}
            onChange={handleChange}
            placeholder="추가 메모"
          />
          <div className={Styles.buttonContainer}>
            <button type="submit" className={Styles.saveButton}>
              생성 완료
            </button>
            <div onClick={handleCheckbox}>
              <MdCheckBox
                onChange={handleCheckbox}
                className={isChecked ? Styles.checkboxActive : ''}
              />
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MarkerModal;
