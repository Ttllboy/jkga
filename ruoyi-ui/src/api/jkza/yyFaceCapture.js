import request from '@/utils/request'

// 查询人脸抓拍列表
export function listYyFaceCapture(query) {
  return request({
    url: '/jkza/yyFaceCapture/list',
    method: 'get',
    params: query
  })
}

// 查询人脸抓拍详细
export function getYyFaceCapture(id) {
  return request({
    url: '/jkza/yyFaceCapture/' + id,
    method: 'get'
  })
}

// 新增人脸抓拍
export function addYyFaceCapture(data) {
  return request({
    url: '/jkza/yyFaceCapture',
    method: 'post',
    data: data
  })
}

// 修改人脸抓拍
export function updateYyFaceCapture(data) {
  return request({
    url: '/jkza/yyFaceCapture',
    method: 'put',
    data: data
  })
}

// 删除人脸抓拍
export function delYyFaceCapture(id) {
  return request({
    url: '/jkza/yyFaceCapture/' + id,
    method: 'delete'
  })
}
