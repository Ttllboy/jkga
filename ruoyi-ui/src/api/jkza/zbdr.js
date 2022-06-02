import request from '@/utils/request'

// 查询值班导入列表
export function listZbdr(query) {
  return request({
    url: '/jkza/zbdr/list',
    method: 'get',
    params: query
  })
}

// 查询值班导入详细
export function getZbdr(id) {
  return request({
    url: '/jkza/zbdr/' + id,
    method: 'get'
  })
}

// 新增值班导入
export function addZbdr(data) {
  return request({
    url: '/jkza/zbdr',
    method: 'post',
    data: data
  })
}

// 修改值班导入
export function updateZbdr(data) {
  return request({
    url: '/jkza/zbdr',
    method: 'put',
    data: data
  })
}

// 删除值班导入
export function delZbdr(id) {
  return request({
    url: '/jkza/zbdr/' + id,
    method: 'delete'
  })
}
