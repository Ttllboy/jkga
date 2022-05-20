import request from '@/utils/request'

// 查询起重设备列表
export function listDtMachine(query) {
  return request({
    url: '/jkza/DtMachine/list',
    method: 'get',
    params: query
  })
}

// 查询起重设备详细
export function getDtMachine(install) {
  return request({
    url: '/jkza/DtMachine/' + install,
    method: 'get'
  })
}

// 新增起重设备
export function addDtMachine(data) {
  return request({
    url: '/jkza/DtMachine',
    method: 'post',
    data: data
  })
}

// 修改起重设备
export function updateDtMachine(data) {
  return request({
    url: '/jkza/DtMachine',
    method: 'put',
    data: data
  })
}

// 删除起重设备
export function delDtMachine(install) {
  return request({
    url: '/jkza/DtMachine/' + install,
    method: 'delete'
  })
}
