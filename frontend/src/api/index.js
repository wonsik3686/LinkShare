import axios from 'axios'
import { API_BASE_URL } from '@/config'

// axios 개체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-type': 'application/json',
    },
  });
  return instance
}

// 이미지 전송용
function apiInstanceMulti() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-type': 'multipart/form-data',
    },
  });
  return instance
}

export { apiInstance, apiInstanceMulti };