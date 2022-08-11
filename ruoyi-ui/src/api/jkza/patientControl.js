import request from '@/utils/request'

// 查询病人管控列表
export function listPatientControl(query) {
  return request({
    url: '/jkza/patientControl/list',
    method: 'get',
    params: query
  })
}

// 查询病人管控详细
export function getPatientControl(id) {
  return request({
    url: '/jkza/patientControl/' + id,
    method: 'get'
  })
}

// 新增病人管控
export function addPatientControl(data) {
  return request({
    url: '/jkza/patientControl',
    method: 'post',
    data: data
  })
}

// 修改病人管控
export function updatePatientControl(data) {
  return request({
    url: '/jkza/patientControl',
    method: 'put',
    data: data
  })
}

// 删除病人管控
export function delPatientControl(id) {
  return request({
    url: '/jkza/patientControl/' + id,
    method: 'delete'
  })
}
